package com.rituals.basket.pooja.market.data.service.core.service;

import com.rituals.basket.pooja.market.data.service.core.model.Banners;
import com.rituals.basket.pooja.market.data.service.core.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannersService {

    private final BannerRepository bannerRepository;

    public Banners create(Banners banners) {
        return bannerRepository.save(banners);
    }

    public Banners get(Long id) {
        return bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found: " + id));
    }

    public List<Banners> getAll() {
        return bannerRepository.findAll();
    }

    public Banners update(Long id, Banners request) {
        Banners existing = new Banners();
        if (id != null) {
            Optional<Banners> existingRecord = bannerRepository.findById(id);
            if (existingRecord.isPresent()) {
                existing = existingRecord.get();
            }
        }
        existing.setBannerName(request.getBannerName());
        existing.setDescription(request.getDescription());

        return bannerRepository.save(existing);
    }

    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }

    public List<Banners> createItems(List<Banners> banners) {
        return banners.stream()
                .map(Banners
                        -> update((Banners.getId()), Banners))
                .toList();
    }
}
