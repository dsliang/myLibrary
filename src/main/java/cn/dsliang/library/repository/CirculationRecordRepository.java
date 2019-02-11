package cn.dsliang.library.repository;

import cn.dsliang.library.entity.CirculationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CirculationRecordRepository extends JpaRepository<CirculationRecord, Integer> {
}
