package com.xforceplus.data.dao;

import com.xforceplus.data.bean.VehicleManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/5/5 0005.
 */
@Repository
public interface VehicleManagementRepository extends JpaRepository<VehicleManagement,Long> {
}
