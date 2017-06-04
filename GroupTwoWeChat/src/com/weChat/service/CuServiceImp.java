package com.weChat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weChat.entity.TreeGrid;
import com.weChat.mapper.CuServiceMapper;

@Service
public class CuServiceImp implements CuService{
	@Autowired
	private CuServiceMapper cuServiceMapper;

	@Override
	public List<TreeGrid> getSelfDefinationalMenu() {
		// TODO Auto-generated method stub
		return this.cuServiceMapper.getSelfDefinationalMenu();
	}
}
