package com.susar.service.susarserviceimplementation;

import com.susar.model.Susar;
import com.susar.repository.SusarRepository;
import com.susar.service.SusarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SusarServiceImplementation implements SusarService {

    private SusarRepository susarRepository;

    @Autowired
    public SusarServiceImplementation(SusarRepository susarRepository) {
        this.susarRepository = susarRepository;
    }

    public Susar create(Susar susar) {
        return susarRepository.save(susar);
    }

    public Susar read(Long susarId) {

        Optional<Susar> susarOptional = susarRepository.findById(susarId);
        return susarOptional.get();
    }

    public void update(Long susarId, Susar susar) {
        //Save up to date susar entity in susars
        susarRepository.save(susar);
    }

    public void delete(Long susarId) {
        //Check if susar exist in database
        if (susarRepository.existsById(susarId)) {
            susarRepository.deleteById(susarId);
        }
    }

    public Iterable<Susar> getAllSusars() {
        return susarRepository.findAll();
    }

    //Maybe filterByType method will be use in further steps
    public List<Susar> filterByType(List<String> types) {
        List<Susar> susars = null;
        Iterable<Susar> iterable = susarRepository.findAll();
        List<Susar> susarsToFilter = new ArrayList<>();
        iterable.forEach(susarsToFilter::add);
        if (types.isEmpty()) {
            susars = susarsToFilter;
        } else {
            susars = susarsToFilter.stream()
                    .filter(susar -> types.contains(susar.getType()))
                    .collect(Collectors.toList());
        }
        return susars;
    }

    public List<String> getTypes() {
        Iterable<Susar> iterable = susarRepository.findAll();
        List<Susar> susarsToFilter = new ArrayList<>();
        iterable.forEach(susarsToFilter::add);
        List<String> allSusarTypes = susarsToFilter.stream()
                .map(susar -> susar.getType())
                .collect(Collectors.toList());
        Collections.sort(allSusarTypes);
        List<String> noDuplicateTypes = new ArrayList<>(
                new HashSet<>(allSusarTypes));
        List<String> noDuplicatesArrayList = new ArrayList<String>(noDuplicateTypes);
        Collections.sort(noDuplicatesArrayList);
        return noDuplicatesArrayList;
    }

    public List<String> getStudies() {
        Iterable<Susar> iterable = susarRepository.findAll();
        List<Susar> susarsToFilter = new ArrayList<>();
        iterable.forEach(susarsToFilter::add);
        List<String> allSusarStudies = susarsToFilter.stream()
                .map(susar -> susar.getStudy())
                .collect(Collectors.toList());
        Collections.sort(allSusarStudies);
        List<String> noDuplicateStudies = new ArrayList<>(
                new HashSet<>(allSusarStudies));
        List<String> noDuplicatesArrayList = new ArrayList<String>(noDuplicateStudies);
        Collections.sort(noDuplicatesArrayList);
        return noDuplicatesArrayList;
    }

    public List<String> getCountries() {
        Iterable<Susar> iterable = susarRepository.findAll();
        List<Susar> susarsToFilter = new ArrayList<>();
        iterable.forEach(susarsToFilter::add);
        List<String> allSusarCountries = susarsToFilter.stream()
                .map(susar -> susar.getCountry())
                .collect(Collectors.toList());
        Collections.sort(allSusarCountries);
        List<String> noDuplicateCountries = new ArrayList<>(
                new HashSet<>(allSusarCountries));
        List<String> noDuplicatesArrayList = new ArrayList<String>(noDuplicateCountries);
        Collections.sort(noDuplicatesArrayList);
        return noDuplicatesArrayList;
    }

    public List<String> getSites() {
        Iterable<Susar> iterable = susarRepository.findAll();
        List<Susar> susarsToFilter = new ArrayList<>();
        iterable.forEach(susarsToFilter::add);
        List<String> allSusarSites = susarsToFilter.stream()
                .map(susar -> susar.getSite())
                .collect(Collectors.toList());
        Collections.sort(allSusarSites);
        List<String> noDuplicateSites = new ArrayList<>(
                new HashSet<>(allSusarSites));
        List<String> noDuplicatesArrayList = new ArrayList<String>(noDuplicateSites);
        Collections.sort(noDuplicatesArrayList);
        return noDuplicatesArrayList;
    }
}