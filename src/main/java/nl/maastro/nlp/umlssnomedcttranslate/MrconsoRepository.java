package nl.maastro.nlp.umlssnomedcttranslate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MrconsoRepository extends JpaRepository<MrconsoEntity, Long> {


    MrconsoEntity findByScui(Long scui);
}
