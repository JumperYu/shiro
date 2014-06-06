package com.my.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.my.entity.Resource;
import com.my.repository.jdbc.ResourceDao;

/**
 * <p>
 * User: Zeng XiangMin
 * <p>
 * Date: 14-06-06
 * <p>
 * Version: 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public Resource createResource(Resource resource) {
		return resourceDao.createResource(resource);
	}

	@Override
	public Resource updateResource(Resource resource) {
		return resourceDao.updateResource(resource);
	}

	@Override
	public void deleteResource(Long resourceId) {
		resourceDao.deleteResource(resourceId);
	}

	@Override
	public Resource findOne(Long resourceId) {
		return resourceDao.findOne(resourceId);
	}

	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	public Set<String> findPermissions(Set<Long> resourceIds) {
		Set<String> permissions = new HashSet<String>();
		for (Long resourceId : resourceIds) {
			Resource resource = findOne(resourceId);
			if (resource != null
					&& !StringUtils.isEmpty(resource.getPermission())) {
				permissions.add(resource.getPermission());
			}
		}
		return permissions;
	}

	@Override
	public List<Resource> findMenus(Set<String> permissions) {
		List<Resource> allResources = findAll();
		List<Resource> menus = new ArrayList<Resource>();
		for (Resource resource : allResources) {
			if (resource.isRootNode()) {
				continue;
			}
			if (resource.getType() != Resource.ResourceType.menu) {
				continue;
			}
			if (!hasPermission(permissions, resource)) {
				continue;
			}
			menus.add(resource);
		}
		return menus;
	}

	private boolean hasPermission(Set<String> permissions, Resource resource) {
		if (StringUtils.isEmpty(resource.getPermission())) {
			return true;
		}
		for (String permission : permissions) {
			if (new WildcardPermission(permission)
					.implies(new WildcardPermission(resource.getPermission()))) {
				return true;
			}
		}
		return false;
	}
}
