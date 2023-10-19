package com.oktay.quizapp.mapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService  {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
