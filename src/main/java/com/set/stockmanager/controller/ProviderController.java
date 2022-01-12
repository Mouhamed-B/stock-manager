package com.set.stockmanager.controller;

import com.set.stockmanager.model.Provider;
import com.set.stockmanager.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/providers")
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /**
     * Create - Insert a provider
     * @param provider - the provider instance
     * @return - the saved provider
     */
    @PostMapping("/")
    public Provider saveProvider(@RequestBody Provider provider){
        return providerService.saveProvider(provider);
    }

    /**
     * Read - retrieve all providers
     * @return - a list of providers
     */
    @GetMapping("/")
    public Iterable<Provider> getProviders(){
        return providerService.getProviders();
    }

    /**
     * Read - retrieve a single provider
     * @param id - provider id
     * @return - a provider instance
     */
    @GetMapping("/{id}")
    public Provider getProvider(@PathVariable("id") int id){
        Optional<Provider> provider = providerService.getProvider(id);
        return provider.orElse(null);

    }

    @PutMapping("/{id}")
    public Provider updateProvider(@PathVariable("id") int id, @RequestBody Provider provider){
        Optional<Provider> u = providerService.getProvider(id);
        if (u.isPresent()){
            Provider currentProvider = u.get();

            String name = provider.getName();
            if (name != null && !name.equals(currentProvider.getName())){
                currentProvider.setName(name);
            }

            String phoneNumber = provider.getPhoneNumber();
            if (phoneNumber!=null && !phoneNumber.equals(currentProvider.getPhoneNumber())){
                currentProvider.setPhoneNumber(provider.getPhoneNumber());
            }

            String email = provider.getEmail();
            if (email!=null && !email.equals(currentProvider.getEmail())){
                currentProvider.setEmail(provider.getEmail());
            }

            return providerService.saveProvider(currentProvider);
        }
        else {
            return null;
        }
    }

    /**
     * Delete - delete a provider
     * @param id - the provider id
     */
    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable("id") int id){
        providerService.deleteProvider(id);
    }
}
