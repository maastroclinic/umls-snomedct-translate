package nl.maastro.nlp.snomedumlslookup.snomedumlslookup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MrconsoRepository extends JpaRepository<MrconsoEntity, Long> {


    MrconsoEntity findByScui(Long scui);
}
