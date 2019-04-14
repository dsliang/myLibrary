package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.*;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.repository.CirculatingRepository;
import cn.dsliang.library.repository.ReaderRepository;
import cn.dsliang.library.repository.RenewRepository;
import cn.dsliang.library.service.RenewService;
import cn.dsliang.library.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RenewServiceImpl implements RenewService {

    @Autowired
    RenewRepository renewRepository;

    @Autowired
    CirculatingRepository circulatingRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Override
    @Transactional
    public void renew(Collection collection) {

        Circulating circulating = circulatingRepository.findByCollectionId(collection.getId());
        if (circulating == null)
            throw new BusinessException(ResultEnum.CIRCULATING_NOT_EXIST);

        if(DateUtil.compareDate(DateUtil.getCurrentDate(),circulating.getReturnDate()) != -1)
            throw new BusinessException(ResultEnum.BORROW_OVERDUE);

        Rule rule = circulating.getReader().getReaderType().getRule();

        //1.检查续借次数
        if (circulating.getRenewalTimes() >= rule.getRenewalTimes()) {
            throw new BusinessException(ResultEnum.UP_TO_RENEW_LIMIT);
        }

        //2.更新图书流通(实时)表应还日期和续借次数
        Date returnDate = DateUtil.addDay(circulating.getReturnDate(), rule.getRenewalDays());
        circulating.setReturnDate(returnDate);
        Integer renewalTimes = circulating.getRenewalTimes();
        renewalTimes++;
        circulating.setRenewalTimes(renewalTimes);

        //3.往“续借记录表”插入一条新记录
        Renew renew = new Renew();
        renew.setCirculationRecord(circulating.getCirculationRecord());
        renew.setRenewalDays(rule.getRenewalDays());

        circulatingRepository.save(circulating);
        renewRepository.save(renew);
    }
}
