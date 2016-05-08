package com.chen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.PageModel.DataGrid;
import com.chen.PageModel.Json;
import com.chen.PageModel.PageHelper;
import com.chen.PageModel.Role;
import com.chen.PageModel.Tree;
import com.chen.dao.BaseDaoI;
import com.chen.dao.impl.BaseDaoImpl;
import com.chen.model.PRole;
import com.chen.model.Tresource;
import com.chen.service.RoleServiceI;
import com.chen.util.PagerUtils;

@Service
public class RoleServiceImpl  implements RoleServiceI {

	
	
	@Autowired
	private BaseDaoI<PRole>  roleDaoI; 
	
	@Autowired
	private BaseDaoI<Tresource> resourceDaoI;
	
	@Override
	public DataGrid dataGrid(PageHelper pageHelper) {
		
		DataGrid dataGrid = new DataGrid();
		List<PRole> pRoles = null;
		List<Role> roles = new ArrayList<Role>();
		String hql = "from PRole p";
		//得到总的记录数
		dataGrid.setTotal(roleDaoI.count("select count(*) "+hql));
		//分页排序后的数据
		pRoles = roleDaoI.find(hql+PagerUtils.orderHql(pageHelper),pageHelper.getPage(),pageHelper.getRows());
		Role oneRole =null;
		for(PRole pRole:pRoles){
			oneRole = new Role();
			BeanUtils.copyProperties(pRole, oneRole);
			//获得角色中的资源id 和 name
			copy(pRole, oneRole);
			roles.add(oneRole);
		}
		dataGrid.setRows(roles);
		
		return dataGrid;
	}


	@Override
	public PRole add(Role role) {
		PRole pRole = new PRole();
		BeanUtils.copyProperties(role, pRole);
		roleDaoI.save(pRole);
		return pRole;
	}


	@Override
	public void deleteById(Integer id) {
		roleDaoI.delete(roleDaoI.get(PRole.class, id));
		
	}


	@Override
	public Role getById(Integer id) {
		PRole pRole = roleDaoI.get(PRole.class, id);
		Role role = new Role();
		BeanUtils.copyProperties(pRole, role);
		copy(pRole, role);
		return role;
	}


	@Override
	public PRole edit(Role role) {
		PRole pRole =roleDaoI.get(PRole.class, role.getId());
		if(role.getName()!=null){
			pRole.setName(role.getName());
		}
		if(role.getDescription()!=null){
			pRole.setDescription(role.getDescription());
		}
		if(role.getSeq()!=null){
			pRole.setSeq(role.getSeq());
		}
		return pRole;
	}
	
	
	//获得角色中的资源id 和 name
	public void copy(PRole pRole,Role oneRole){
		String ids="";
		String names="";
		boolean flag =false;
		if(pRole.getResources()!=null&&pRole.getResources().size()>0){
			for(Tresource tresource:pRole.getResources()){

				if(flag){
					ids+=",";
					names+=",";
				}else{
					flag=true;
					
				}
				ids+=tresource.getId();
				names+=tresource.getName();
			}
			oneRole.setResourcesId(ids);
			oneRole.setResourcesName(names);
		}
	}


	@Override
	public void grant(Role role) {
		PRole pRole = roleDaoI.get(PRole.class, role.getId());
		String [] ids =null;
		if(role.getResourcesId()!=null&&!role.getResourcesId().equals("")){
			ids =role.getResourcesId().split(",");
			String resourcesid="";
			boolean flag =false;
			if(ids!=null&&!ids.equals(""))
			{
				
				for(String id:ids){
					if(flag){
						resourcesid+=",";
					}else{
						flag=true;
					}
					resourcesid+="'"+id+"'";
				}
				pRole.setResources(new HashSet<Tresource>(resourceDaoI.find("select distinct t from Tresource t where t.id in (" + resourcesid + ")")));
			}
			else
			{
				pRole.setResources(null);
			}
		}
		
	}


	@Override
	public List<Tree> tree() {
		List<Tree> lt = new ArrayList<Tree>();
		List<PRole> roles =null;
		roles=roleDaoI.find("from PRole");
		for(PRole pRole:roles){
			Tree tree = new Tree();
			tree.setId(Integer.toString(pRole.getId()));
			tree.setText(pRole.getName());
			lt.add(tree);
			
		}
		return lt;
	}



	
}
