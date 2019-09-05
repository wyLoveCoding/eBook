package com.nuc.service.impl;

import com.nuc.mapper.ShopMapper;
import com.nuc.pojo.Book;
import com.nuc.pojo.Shop;
import com.nuc.pojo.User;
import com.nuc.service.ShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Resource
    private ShopMapper shopMapper;

    @Override
    //查询所有购物车信息
    public List<Shop> listShop(User user) {
        return shopMapper.selAllShop(user);
    }

    @Override
    //
    public boolean addShop(Book book, User user) {
        return shopMapper.insShop(book,user);
    }

    @Override
    public boolean deleteShop(Shop shop) {
        return shopMapper.delShop(shop);
    }

    @Override
    public boolean updateShop(Shop shop) {
        return shopMapper.updShop(shop);
    }

    @Override
    public boolean sumMoney(User user,String ids[]) {
        return shopMapper.sumMoney(user,ids);
    }
}
