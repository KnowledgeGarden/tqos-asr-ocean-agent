/**
 * 
 */
package org.topicquests.os.asr.info;

import org.topicquests.os.asr.info.api.IDocument;
import org.topicquests.os.asr.info.api.IEnterprise;
import org.topicquests.os.asr.info.api.IInfoOcean;
import org.topicquests.os.asr.info.api.IPerson;
import org.topicquests.os.asr.info.api.ITriple;
import org.topicquests.os.asr.wordgram.WordGramEnvironment;
import org.topicquests.os.asr.wordgram.WordGramModel;
import org.topicquests.support.api.IResult;
import org.topicquests.support.util.LRUCache;

import com.tinkerpop.blueprints.Edge;
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


	/**
	 * @param env
	 */
	public InformationOceanModel(WordGramEnvironment env) {
		super(env);
		wgEnvironment = env;
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
		SqlVertex v = (SqlVertex)graph().addVertex(id, label);
		ITriple result = new TripleNode(v, wgEnvironment);
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public IPerson createPersonNode(String id, String label) {
		SqlVertex v = (SqlVertex)graph().addVertex(id, label);
		IPerson result = new PersonNode(v, wgEnvironment);
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public IEnterprise createEnterpriseNode(String id, String label) {
		SqlVertex v = (SqlVertex)graph().addVertex(id, label);
		IEnterprise result = new EnterpriseNode(v, wgEnvironment);
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public IDocument createDocumentNode(String id, String label, String docId) {
		SqlVertex v = (SqlVertex)graph().addVertex(id, label);
		IDocument result = new DocumentNode(v, wgEnvironment);
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public IResult getTriple(String id) {
		IResult result = null;
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public IResult getPerson(String id) {
		IResult result = null;
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public IResult getEnterpriseNode(String id) {
		IResult result = null;
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public IResult getDocumentNode(String id) {
		IResult result = null;
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Edge connectWordGramsBySentenceId(String wgFromId, String wgToId, String sentenceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge connectWordGramToTupleBySentenceId(String wgFromId, String tpToId, String sentenceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge connectAuthorToDocument(String auFromId, String docToId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge connectAuthorToEmployer(String auFromId, String empToId) {
		// TODO Auto-generated method stub
		return null;
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

}
