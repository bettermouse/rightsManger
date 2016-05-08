package com.chen.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.PageModel.DataGrid;
import com.chen.PageModel.PageHelper;
import com.chen.PageModel.User;
import com.chen.dao.BaseDaoI;
import com.chen.model.MyException;
import com.chen.model.PRole;
import com.chen.model.PUser;
import com.chen.service.UserServiceI;
import com.chen.util.StringUtil;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	BaseDaoI<PUser> userDaoI;
	@Autowired
	BaseDaoI<PRole> roleDaoI;

	@Override
	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		PUser pUser = userDaoI
				.get("from PUser p where p.username = :username and p.password = :password and status=1",
						params);
		// 返回的用户
		User rUser = null;
		if (pUser != null) {
			rUser = new User();
			BeanUtils.copyProperties(pUser, rUser);
		
			return rUser;
		}
		return rUser;

	}

	@Override
	public DataGrid dataGrid(User user, PageHelper pageHelper) {

		DataGrid dg = new DataGrid();
		List<PUser> pUsers = null;
		List<User> users = new ArrayList<User>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from PUser p ";
		pUsers = userDaoI.find(hql + whereHql(user, params)
				+ orderHql(pageHelper), params, pageHelper.getPage(),
				pageHelper.getRows());

		for (PUser pUser : pUsers) {
			User oneUser = new User();
			BeanUtils.copyProperties(pUser, oneUser);
			users.add(oneUser);
		}
		dg.setRows(users);
		dg.setTotal(userDaoI.count(
				"select count(*) " + hql + whereHql(user, params), params));
		return dg;
	}

	private String whereHql(User user, Map<String, Object> params) {
		String hql = "";
		if (user != null) {
			hql += " where 1=1 ";
			if (StringUtil.isNull(user.getUsername())) {
				hql += " and  p.username like :username ";
				params.put("username", "%" + user.getUsername() + "%");
			}
			if(user.getCreateDate()!=null){
				hql += " and  p.createDate >= :createDate ";
				params.put("createDate", user.getCreateDate());
			}
			if(user.getEndDate()!=null){
				hql += " and  p.createDate <= :endDate ";
				params.put("endDate", user.getEndDate());
			}
		}
		return hql;
	}

	private String orderHql(PageHelper ph) {
		String orderString = "";
		if (ph.getSort() != null && ph.getOrder() != null) {
			orderString = " order by p." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}

	@Override
	public PUser add(User user) {
		PUser pUser = new PUser();
		if (getUser(user.getUsername()) != null) {
			throw new MyException("用户已存在");
		}
		BeanUtils.copyProperties(user, pUser);
		pUser.setPassword("123456");
		pUser.setCreateDate(new Date());
		pUser.setStatus(0);
		userDaoI.save(pUser);
		return pUser;
	}

	@Override
	public PUser getUser(String username) {
		PUser pUser = (PUser) userDaoI.get("from PUser p where p.username='"
				+ username + "'");
		return pUser;
	}

	@Override
	public void delete(Integer id) {
		PUser pUser = userDaoI.get(PUser.class, id);

		userDaoI.delete(pUser);
	}

	@Override
	public void status(Integer id) {
		PUser pUser = userDaoI.get(PUser.class, id);
		if(pUser.getStatus()==0){
			pUser.setStatus(1);
		}else{
			pUser.setStatus(0);
		}

	}

	@Override
	public void rePwd(Integer id) {
		PUser pUser = userDaoI.get(PUser.class, id);
		pUser.setPassword("123456");
		
	}

	@Override
	public User get(Integer id) {
		PUser pUser = userDaoI.get(PUser.class, id);
		User user = new User();
		BeanUtils.copyProperties(pUser, user);
		if(pUser.getRoles()!=null){
			boolean flag =true;
			String roleIds="";
			String roleNames="";
			for(PRole pRole:pUser.getRoles()){
				if(flag){
					flag=false;
				}else{
					roleNames+=",";
					roleIds+=",";
				}
				roleIds+=pRole.getId();
				roleNames+=pRole.getName();
				user.setRoleIds(roleIds);
				user.setRoleNames(roleNames);
			}
		}
		return user;
	}

	@Override
	public void edit(User user) {
		PUser pUser = userDaoI.get(PUser.class,user.getId());
		pUser.setPhone(user.getPhone());
		
	}

	@Override
	public void grnat(User user) {
		PUser pUser = userDaoI.get(PUser.class, user.getId());
		if(StringUtil.isNull(user.getRoleIds())){
			String roleids =user.getRoleIds();
			String[] ids=roleids.split(",");
			boolean flag =true;
			String  dRoleId ="";
			for(String id:ids){
				if(flag){
					flag=false;
				}else{
					dRoleId+=",";
				}
				dRoleId+=id;
			}
			List<PRole> roles =roleDaoI.find("from PRole p where p.id in ("+dRoleId+")");
			pUser.setRoles(new HashSet<PRole>(roleDaoI.find("from PRole p where p.id in ("+dRoleId+")")));
			
		}else{
			pUser.setRoles(null);
		}
		
	}

	@Override
	public boolean editCurrentPwd(Integer id, String pwd, String newPwd) {
		PUser pUser = userDaoI.get(PUser.class, id);
		if(pUser.getPassword().equals(pwd)){
			pUser.setPassword(newPwd);
			return true;
		}
		return false;
	}

	@Override
	public boolean editCurrentMessage(Integer id, User user) {
		PUser pUser = userDaoI.get(PUser.class, id);
		pUser.setPhone(user.getPhone());
		return true;
	}

}
