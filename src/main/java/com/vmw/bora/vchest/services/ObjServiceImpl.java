package com.vmw.bora.vchest.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmw.bora.vchest.domain.Obj;
import com.vmw.bora.vchest.repo.cassandra.ObjCassandraRepo;
import com.vmw.bora.vchest.repo.solr.ObjSolrRepo;

@Service
@Transactional
public class ObjServiceImpl {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ObjCassandraRepo objCassandraRepo;

	@Autowired
	private ObjSolrRepo objSolrRepo;

	public void save(Obj obj) {
		objCassandraRepo.save(obj);
		objSolrRepo.save(obj);
	}

	public void delete(Obj obj) {
		if ((objCassandraRepo.exists(obj.toString()) != true)
				|| (objSolrRepo.exists(obj.toString()) != true)) {
			logger.error("Delete failed following object not found bucket:"
					+ obj.getBucketName() + " uri:" + obj.getLocationUri());
			throw new RuntimeException();
		}
		objCassandraRepo.delete(obj);
		objSolrRepo.delete(obj);
	}
}