/**
 * 
 */
package org.topicquests.os.asr.info;

import org.topicquests.os.asr.info.api.IDocument;

import java.util.UUID;

import org.topicquests.hyperbrane.api.IWordGram;
import org.topicquests.os.asr.info.api.IAgent;
import org.topicquests.os.asr.info.api.IInfoOcean;
import org.topicquests.os.asr.info.api.IOceanConstants;
import org.topicquests.os.asr.info.api.IPerson;
import org.topicquests.os.asr.info.api.ITriple;
import org.topicquests.os.asr.wordgram.WordGramEnvironment;
import org.topicquests.os.asr.wordgram.WordGramModel;
import org.topicquests.pg.PostgresConnectionFactory;
import org.topicquests.pg.api.IPostgresConnection;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;
import org.topicquests.support.util.LRUCache;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.sql.SqlGraph;
import com.tinkerpop.blueprints.impls.sql.SqlVertex;

/**
 * @author jackpark
 *
 */
public class InformationOceanModel extends WordGramModel implements IInfoOcean {
	private InformationEnvironment environment;
	private WordGramEnvironment wgEnvironment;
	private LRUCache personCache;
	private LRUCache documentCache;
	private LRUCache tripleCache;
	private SqlGraph graph;
	private PostgresConnectionFactory provider;



	/**
	 * @param env
	 */
	public InformationOceanModel(WordGramEnvironment env) {
		super(env);
		wgEnvironment = env;
		graph = wgEnvironment.getSqlGraph();
		provider = graph.getProvider();
		personCache = new LRUCache(2048);
		documentCache = new LRUCache(2048);
		tripleCache = new LRUCache(2048);
	}
	
	/**
	 * Utility method to make use of {@link SQLGraph}
	 * @return
	 */
	SqlGraph graph() {
		return wgEnvironment.getSqlGraph();
	}
	
	@Override
	public void setEnvironment(InformationEnvironment env) {
		environment = env;
	}

	@Override
	public ITriple createTripleNode(String id, String label) {
		ITriple result = null;
    	IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
        try {
        	conn = provider.getConnection();
           	conn.setProxyRole(r);
            conn.beginTransaction(r);
            SqlVertex v = (SqlVertex)graph().addVertex(conn, id, label, r);
            v.setProperty(conn, IOceanConstants.OCEAN_NODE_TYPE, IOceanConstants.DOCUMENT_TYPE, r);
            result = new TripleNode(v, wgEnvironment);
            conn.endTransaction(r);
        } catch (Exception e) {
        	e.printStackTrace();
        	environment.logError(e.getMessage(), e);
        } finally {
	    	conn.closeConnection(r);
        } 
		return result;
	}
/*
	@Override
	public IPerson createPersonNode(String id, String label) {
		SqlVertex v = (SqlVertex)graph().addVertex(id, label);
		IPerson result = new PersonNode(v, wgEnvironment);
		// TODO Auto-generated method stub
		return result;
	}
*/
	@Override
	public IAgent createInstitutionNode(String label) {
		IAgent result = null;
    	IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
        try {
        	conn = provider.getConnection();
           	conn.setProxyRole(r);
            conn.beginTransaction(r);
            IResult x = super.processString(conn, label, "SystemUser", null, r);
            String gid = (String)x.getResultObject();
            SqlVertex v = (SqlVertex)graph().getVertex(gid, conn, r);
            v.setProperty(conn, IOceanConstants.OCEAN_NODE_TYPE, IOceanConstants.INSTITUTION_TYPE, r);
            result = new InstitutionNode(v, wgEnvironment);
            conn.endTransaction(r);
        } catch (Exception e) {
        	e.printStackTrace();
        	environment.logError(e.getMessage(), e);
        } finally {
	    	conn.closeConnection(r);
        }           
		return result;
	}

	@Override
	public IDocument createDocumentNode(String id, String label, String docId) {
		IDocument result = null;
    	IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
        try {
        	conn = provider.getConnection();
           	conn.setProxyRole(r);
            conn.beginTransaction(r);
            SqlVertex v = (SqlVertex)graph().addVertex(conn, id, label, r);
            v.setProperty(conn, IOceanConstants.OCEAN_NODE_TYPE, IOceanConstants.DOCUMENT_TYPE, r);
            result = new DocumentNode(v, wgEnvironment);
            conn.endTransaction(r);
        } catch (Exception e) {
        	e.printStackTrace();
        	environment.logError(e.getMessage(), e);
            //    throw new SqlGraphException(e);
        } finally {
	    	conn.closeConnection(r);
        }           
		return result;
	}
	

	@Override
	public IResult getTriple(String id) {
		IResult result = new ResultPojo();
		SqlVertex v = graph.getVertex(id);
		TripleNode tn = new TripleNode(v, wgEnvironment);
		result.setResultObject(tn);
		return result;
	}
/*
	@Override
	public IResult getPerson(String id) {
		IResult result = new ResultPojo();
		IWordGram g = getThisWordGram(id);
		result.setResultObject(g);
		return result;
	}
*/
	@Override
	public IResult getInstitutionNode(String id) {
		IResult result = new ResultPojo();
		SqlVertex v = graph.getVertex(id);
		InstitutionNode in = new InstitutionNode(v, wgEnvironment);
		result.setResultObject(in);
		return result;
	}

	@Override
	public IResult getDocumentNode(String id) {
		IResult result = new ResultPojo();
		SqlVertex v = graph.getVertex(id);
		DocumentNode dn = new DocumentNode(v, wgEnvironment);
		result.setResultObject(dn);
		return result;
	}

	@Override
	public Edge connectWordGramsBySentenceId(String wgFromId, String wgToId, String sentenceId) {
		environment.logDebug("IOM.cWbS "+wgFromId+" "+wgToId+"\n"+graph);
    	IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    Edge result = null;
        try {
        	conn = provider.getConnection();
           	conn.setProxyRole(r);
            conn.beginTransaction(r);
    		result = graph.addEdge(conn, UUID.randomUUID().toString(), wgFromId, wgToId, IOceanConstants.SENTENCE_LINK+"."+sentenceId, r);
    		environment.logDebug("IOM.cWbS-2 "+result);

            conn.endTransaction(r);
        } catch (Exception e) {
        	e.printStackTrace();
        	environment.logError(e.getMessage(), e);
        } finally {
	    	conn.closeConnection(r);
        }
		return result;	
	}

	@Override
	public Edge connectWordGramToTupleBySentenceId(String wgFromId, String tpToId, String sentenceId) {
		environment.logDebug("IOM.cWTbS "+wgFromId+" "+tpToId+"\n"+graph);
    	IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    Edge result = null;
        try {
        	conn = provider.getConnection();
           	conn.setProxyRole(r);
            conn.beginTransaction(r);
    		result = graph.addEdge(conn, UUID.randomUUID().toString(), wgFromId, tpToId, IOceanConstants.TRIPLE_LINK+"."+sentenceId, r);
    		environment.logDebug("IOM.cWTbS-2 "+result);

            conn.endTransaction(r);
        } catch (Exception e) {
        	e.printStackTrace();
        	environment.logError(e.getMessage(), e);
        } finally {
	    	conn.closeConnection(r);
        }
		return result;		}

	@Override
	public Edge connectAuthorToDocument(String auFromId, String docToId) {
		environment.logDebug("IOM.cA2D "+auFromId+" "+docToId+"\n"+graph);
    	IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    Edge result = null;
        try {
        	conn = provider.getConnection();
           	conn.setProxyRole(r);
            conn.beginTransaction(r);
    		result = graph.addEdge(conn, UUID.randomUUID().toString(), auFromId, docToId, IOceanConstants.AUTHOR_LINK, r);
    		environment.logDebug("IOM.cA2D-2 "+result);

            conn.endTransaction(r);
        } catch (Exception e) {
        	e.printStackTrace();
        	environment.logError(e.getMessage(), e);
            //    throw new SqlGraphException(e);
        } finally {
	    	conn.closeConnection(r);
        }
		return result;
	}

	@Override
	public Edge connectAuthorToInstitution(String auFromId, String empToId) {
		environment.logDebug("IOM.cA2E "+auFromId+" "+empToId+"\n"+graph);
    	IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    Edge result = null;
        try {
        	conn = provider.getConnection();
           	conn.setProxyRole(r);
            conn.beginTransaction(r);
    		result = graph.addEdge(conn, UUID.randomUUID().toString(), auFromId, empToId, IOceanConstants.EMPLOYER_LINK, r);
    		environment.logDebug("IOM.cA2E-2 "+result);
            conn.endTransaction(r);
        } catch (Exception e) {
        	e.printStackTrace();
        	environment.logError(e.getMessage(), e);
            //    throw new SqlGraphException(e);
        } finally {
	    	conn.closeConnection(r);
        }
		return result;
	}

	@Override
	public Edge connectAuthorToFunder(String auFromId, String funToId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge connectDocumentToFunder(String docFromId, String funToId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge connectEmployerFunder(String empFromId, String funToId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge connectKeyWordGramToDocument(String keyWordId, String documentId) {
		environment.logDebug("IOM.cK2D "+keyWordId+" "+documentId+"\n"+graph);
    	IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    Edge result = null;
        try {
        	conn = provider.getConnection();
           	conn.setProxyRole(r);
            conn.beginTransaction(r);
    		result = graph.addEdge(conn, UUID.randomUUID().toString(), keyWordId, documentId, IOceanConstants.EMPLOYER_LINK, r);
    		environment.logDebug("IOM.cK2D-2 "+result);
            conn.endTransaction(r);
        } catch (Exception e) {
        	e.printStackTrace();
        	environment.logError(e.getMessage(), e);
            //    throw new SqlGraphException(e);
        } finally {
	    	conn.closeConnection(r);
        }
		return result;
	}

	@Override
	public IResult findByLabel(String label) {
		IResult result = new ResultPojo();
		//TODO
		return result;
	}
	
	@Override
	public IResult findByKeyValuePair(String key, String value) {
		IResult result = new ResultPojo();
		//TODO
		return result;
	}

}
