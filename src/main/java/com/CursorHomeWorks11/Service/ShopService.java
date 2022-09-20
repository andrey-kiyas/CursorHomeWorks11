package com.CursorHomeWorks11.Service;

import com.CursorHomeWorks11.Entity.ShopDTO;
import com.CursorHomeWorks11.Entity.Shop;
import com.CursorHomeWorks11.Exception.ShopNotFoundException;
import com.CursorHomeWorks11.Repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public List<Shop> getShopList() {
        return shopRepository.findAll();
    }

    public void createShop(Shop shop) {
        shopRepository.save(shop);
        System.out.println("shop added");
    }

    public void createShopDTO(ShopDTO shopDto) {
        Shop shop = convertToEntity(shopDto);
        shopRepository.save(shop);
    }

    public Object findByShopId(long id) {
        Optional<Shop> findProductById = shopRepository.findById(id);
        return findProductById
                .orElseThrow(null);
    }

//    public Shop findByShopId(Long id) {
//        isExists(id);
//        return shopRepository.findById(id).orElse(null);
//    }

    public void deleteShop(Long id) {
        shopRepository.deleteById(id);
    }

    public void updateShop(Shop shop, Long id) {
        Shop shopReqBody;
        if (!shopRepository.existsById(id)) {
            shopReqBody = shopRepository.findById(id).orElse(null);
            assert shopReqBody != null;
            shopReqBody.setCity(shop.getCity());
            shopReqBody.setStreet(shop.getStreet());
            shopReqBody.setName(shop.getName());
            shopReqBody.setStaff(shop.getStaff());
            shopReqBody.setSiteAvail(shop.isSiteAvail());
            shopRepository.save(shopReqBody);
        }
    }

    private Shop convertToEntity(ShopDTO shopDto) {
        Shop shop = new Shop();
        shop.setName(shopDto.getName());
        shop.setCity(shopDto.getCity());
        shop.setStreet(shopDto.getStreet());
        shop.setSiteAvail(shopDto.isSiteAvail());
        return shop;
    }

    public void isExists(Long id) {
        boolean isExist = shopRepository.existsById(id);
        if (!isExist) throw new ShopNotFoundException("Shop \"id=" + id + "\" no found");
    }
}
