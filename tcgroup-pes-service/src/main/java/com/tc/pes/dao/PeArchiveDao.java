package com.tc.pes.dao;

import com.tc.pes.model.PeArchive;
import com.tc.pes.model.PeArchiveCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PeArchiveDao extends SingleTableDao<PeArchive, PeArchiveCriteria> {
}