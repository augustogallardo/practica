package ar.com.practicaprof.servicesimp;

import java.util.List;
import javax.transaction.Transactional;

import ar.com.practicaprof.dao.IRolesDao;
import ar.com.practicaprof.entities.Roles;
import ar.com.practicaprof.services.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RolesService implements IRolesService {

    @Autowired
    private IRolesDao entityDao;

    public List<Roles> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Roles> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Roles> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Roles get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Roles entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Roles entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
