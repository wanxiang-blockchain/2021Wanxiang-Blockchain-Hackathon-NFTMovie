package com.pku.ss.movienftserver.service;

import com.pku.ss.movienftserver.data.Page;
import com.pku.ss.movienftserver.data.enums.Copyright;

public interface CopyrightService {

    Page<Copyright> batchGetCopyright(int currentPage, int pageSize);

}
