package com.dicore.fatura_yonetim_sistemi.service;

import com.dicore.fatura_yonetim_sistemi.dtos.request.BillRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.BillResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Bill;
import com.dicore.fatura_yonetim_sistemi.entity.ServiceProvider;
import com.dicore.fatura_yonetim_sistemi.exception.EntityNotFoundException;
import com.dicore.fatura_yonetim_sistemi.mapper.BillMapper;
import com.dicore.fatura_yonetim_sistemi.repository.BillRepository;
import com.dicore.fatura_yonetim_sistemi.repository.ServiceProviderRepository;
import com.dicore.fatura_yonetim_sistemi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    @Autowired
    public BillService(BillRepository billRepository, UserRepository userRepository, ServiceProviderRepository serviceProviderRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    public List<BillResponseDTO> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream()
                .map(BillMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public BillResponseDTO getBillById(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new EntityNotFoundException("Bill", billId));
        return BillMapper.toResponseDTO(bill);
    }

    @Transactional
    public BillResponseDTO createBill(BillRequestDTO billRequestDTO) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(billRequestDTO.getServiceProviderId())
                .orElseThrow(() -> new EntityNotFoundException("ServiceProvider", billRequestDTO.getServiceProviderId()));

        Bill bill = BillMapper.toEntity(billRequestDTO,
                userRepository.findById(billRequestDTO.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User", billRequestDTO.getUserId())),
                serviceProvider);

        Bill savedBill = billRepository.save(bill);

        return BillMapper.toResponseDTO(savedBill);
    }

    public void deleteBill(Long billId) {
        boolean exists = billRepository.existsById(billId);
        if (!exists) {
            throw new EntityNotFoundException("Bill", billId);
        }
        billRepository.deleteById(billId);
    }
}
