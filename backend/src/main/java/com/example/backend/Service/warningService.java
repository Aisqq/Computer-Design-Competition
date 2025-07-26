package com.example.backend.Service;

import com.example.backend.Controller.request.warningPageRequest;
import com.example.backend.Entity.warning;

import java.util.List;
import java.util.Map;

public interface warningService {
    List<warning> Selectwarnings();
    Object page(warningPageRequest warningPageRequest);
    List<Map<String,Integer>> Selectwarningtype();
    List<Map<Integer,String>> Selectwarninglevel();
}
