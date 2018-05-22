package com.xforceplus.data.dao;

import com.xforceplus.data.bean.UnitInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2018/5/5 0005.
 */
@Repository
public interface UnitInformationRepository extends JpaRepository<UnitInformation,Long> {
    Page<UnitInformation> findAll(Pageable pageable);
}
