package com.rssreader.persistence.transformer;

import com.rssreader.model.BaseModel;
import com.rssreader.persistence.dao.BaseDAO;

public interface INewsTransformer<TDAO extends BaseDAO, TModel extends BaseModel> {

    abstract TDAO transformToDAO(TModel baseModel);

    abstract TModel transformToModel(TDAO baseDAO);

}
