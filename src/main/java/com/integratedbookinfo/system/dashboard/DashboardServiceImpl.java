package com.integratedbookinfo.system.dashboard;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

	private final DashboardMapper dashboardMapper;
}
