package com.example.myShop.services;

import com.example.myShop.models.Ordering;
import com.example.myShop.repositories.ClientRepository;
import com.example.myShop.repositories.OrderingRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class OrderingService {
    private final OrderingRepository orderingRepository;
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public OrderingService(OrderingRepository orderingRepository, ClientRepository clientRepository, ClientService clientService) {
        this.orderingRepository = orderingRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    public List<Ordering> getOrderListByClient_ID(Integer id){
        return orderingRepository.findByClientID(id);
    }

    public void saveOrder(Ordering order) {
        orderingRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        orderingRepository.deleteById(id);
    }

    public Ordering getOrderById(Integer id) {
        return orderingRepository.findById(id).orElse(null);
    }

    public List<Ordering> getOrderListByGoods_ID(Integer id){
        return orderingRepository.findByGoodsID(id);
    }

    public List<Ordering> getOrdersList(){
        return orderingRepository.findAll();
    }

    public List<Ordering> getOrderListByPayMethod(String id){
        return orderingRepository.findByPayMethod(id);
    }

    public List<Ordering> getOrderListByReceiveId(Integer id){
        return orderingRepository.findByReceiveID(id);
    }

    public List<Ordering> getOrderListByStatusName(String id){
        return orderingRepository.findByStatusName(id);
    }
}
