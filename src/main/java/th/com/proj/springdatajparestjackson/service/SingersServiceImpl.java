package th.com.proj.springdatajparestjackson.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import th.com.proj.springdatajparestjackson.entity.Singers;
import th.com.proj.springdatajparestjackson.repository.SingersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor    // final붙은 변수의 생성자를 만들어서 주입해줌
public class SingersServiceImpl implements SingersService {

    private final SingersRepository singersRepository;

    @Override
    @Transactional
    public Integer saveSingers(Singers singers) {
        return singersRepository.save(singers).getSingersPosition();
    }

    @Override
    @Transactional
    public void update(Singers singers) {
        singersRepository.save(singers);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        singersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Singers getOneSinger(Integer id) {
        return singersRepository.findById(id).orElse(null); //TODO orElse = Exception ?
    }


    @Override
    public List<Singers> getAllSingers() {
        return singersRepository.findAll(); //FIXME
    }


    @Override
    @Transactional
    public boolean isAvailable(Integer id) {
        return singersRepository.existsById(id);
    }
}
