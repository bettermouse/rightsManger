package com.chen.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.chen.PageModel.ResourceType;
import com.chen.dao.BaseDaoI;
import com.chen.model.Tresourcetype;
import com.chen.service.ResourceTypeServiceI;


@Service
public class ResourceTypeServiceImpl implements ResourceTypeServiceI {

	@Autowired
	private BaseDaoI<Tresourcetype>  resourceTypeDaoI;

	@Override
	@Cacheable(value = "resourceTypeServiceCache", key = "'resourceTypeList'")
	public List<ResourceType> getResourceTypeList() {
		List<Tresourcetype> l = resourceTypeDaoI.find("from Tresourcetype t");
		List<ResourceType> rl = new ArrayList<ResourceType>();
		if (l != null && l.size() > 0) {
			for (Tresourcetype t : l) {
				ResourceType rt = new ResourceType();
				BeanUtils.copyProperties(t, rt);
				rl.add(rt);
			}
		}
		return rl;
	}

}
