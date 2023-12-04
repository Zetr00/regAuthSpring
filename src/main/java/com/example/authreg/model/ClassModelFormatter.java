package com.example.authreg.model;

import com.example.authreg.repo.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ClassModelFormatter implements Formatter<ClassModel> {

    private final ClassRepo classRepo;

    @Autowired
    public ClassModelFormatter(ClassRepo classRepo) {
        this.classRepo = classRepo;
    }

    @Override
    public ClassModel parse(String id, Locale locale) throws ParseException {
        // Преобразование строки (ID класса) в объект ClassModel
        Long classId = Long.parseLong(id);
        return classRepo.findById(classId).orElse(null);
    }

    @Override
    public String print(ClassModel classModel, Locale locale) {
        // Преобразование объекта ClassModel в строку (обычно возвращается ID класса)
        return String.valueOf(classModel.getId());
    }
}
