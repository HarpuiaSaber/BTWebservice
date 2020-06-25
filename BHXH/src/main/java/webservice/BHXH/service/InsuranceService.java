package webservice.BHXH.service;

import java.util.List;

import webservice.BHXH.entity.Insurance;
import webservice.BHXH.model.dto.InsuranceDto;
import webservice.BHXH.model.search.InsuranceSearch;

public interface InsuranceService extends BaseService<InsuranceDto, Long> {

    List<InsuranceDto> search(InsuranceSearch search);

    List<InsuranceDto> searchWithPaging(InsuranceSearch search);

    long countTotal(InsuranceSearch search);

    InsuranceDto getByUser(Long userId);

}
