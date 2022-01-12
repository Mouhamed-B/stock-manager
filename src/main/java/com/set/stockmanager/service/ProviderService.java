package com.set.stockmanager.service;

import com.set.stockmanager.model.Provider;
import com.set.stockmanager.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    /**
     * Create - Insert a provider
     * @param provider - the provider instance
     * @return - the saved provider
     */
    public Provider saveProvider(Provider provider){
        return providerRepository.save(provider);
    }

    /**
     * Read - retrieve all providers
     * @return - a list of providers
     */
    public Iterable<Provider> getProviders(){
        return providerRepository.findAll();
    }

    /**
     * Read - retrieve a single provider
     * @param id - provider id
     * @return - a provider instance
     */
    public Optional<Provider> getProvider(final int id){
        return providerRepository.findById(id);
    }

    /**
     * Delete - delete a provider
     * @param id - the provider id
     */
    public void deleteProvider(final int id){
        providerRepository.deleteById(id);
    }
}
