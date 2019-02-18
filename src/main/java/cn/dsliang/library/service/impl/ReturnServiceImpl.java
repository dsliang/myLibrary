package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Circulating;
import cn.dsliang.library.entity.CirculationRecord;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Return;
import cn.dsliang.library.enums.CirculationRecordStatusEnum;
import cn.dsliang.library.enums.CollectionStatusEnum;
import cn.dsliang.library.repository.CirculatingRepository;
import cn.dsliang.library.repository.ReturnRepository;
import cn.dsliang.library.service.ReturnService;
import cn.dsliang.library.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    CirculatingRepository circulatingRepository;

    @Autowired
    ReturnRepository returnRepository;

    @Override
    @Transactional
    public void returns(Collection collection) {
        Circulating circulating = circulatingRepository.findByCollectionId(collection.getId());

        //1.更新馆藏状态
        Collection coll = circulating.getCollection();
        coll.setStatus(CollectionStatusEnum.NORMAL.getCode());

        //2.往图书归还记录表插入一条新记录
        Return returns = new Return();
        returns.setCirculationRecord(circulating.getCirculationRecord());
        returns.setReturnedDate(DateUtil.getCurrentDate());
        returnRepository.save(returns);

        //3.更新图书流通记录表起始日期,应该还日期,归还日期和状态
        CirculationRecord circulationRecord = circulating.getCirculationRecord();
        circulationRecord.setBorrowDate(circulating.getBorrowDate());
        circulationRecord.setReturnDate(circulating.getReturnDate());
        circulationRecord.setReturnedDate(DateUtil.getCurrentDate());
        if (DateUtil.compareDate(circulationRecord.getReturnedDate(), circulationRecord.getReturnDate()) == 1) {
            circulationRecord.setStatus(CirculationRecordStatusEnum.Overdue.getCode());
        } else {
            circulationRecord.setStatus(CirculationRecordStatusEnum.Punctual.getCode());
        }

        //4.删除图书流通(实时)表对应记录
        circulatingRepository.delete(circulating);
    }
}
