package sk.app.model.mapper;

import org.springframework.stereotype.Component;
import sk.app.model.dto.TransferDto;
import sk.app.model.entities.Transfer;

import java.util.List;

@Component
public class TransferMapper implements ConvertService<TransferDto, Transfer> {

    @Override
    public TransferDto toDto(Transfer transfer) {
        TransferDto transferDto = new TransferDto();

        transferDto.setId(transfer.getId());
        transferDto.setAmount(transfer.getAmount());
        transferDto.setDate(transfer.getDate());
        transferDto.setCreditorIban(transfer.getCreditorIban());
        transferDto.setDebtorIban(transfer.getDebtorIban());
        transferDto.setMessage(transfer.getMessage());
        transferDto.setAccountId(transfer.getAccountId());

        return transferDto;
    }

    @Override
    public List<TransferDto> toDtoList(List<Transfer> transferList) {
        return transferList.stream()
                .map(this::toDto)
                .toList();
    }
}
