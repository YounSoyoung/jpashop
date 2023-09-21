package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);     //id를 기반으로 실제 DB에 있는 영속성 엔티티를 찾아온다.
        findItem.setPrice(price);    //찾은 영속성 엔티티에 param으로 받은 책의 필드값들을 설정해준다.
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        //스프링의 @Transactional에 의해 트랜젝션이 커밋된다. -> JPA가 flush를 날린다. => 영속성 엔티티인 findItem의 변경을 감지한 후 업데이트 처리

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
