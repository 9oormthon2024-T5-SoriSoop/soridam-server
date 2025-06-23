package sorisoop.soridam.infra.repository.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import sorisoop.soridam.domain.place.place.document.PlaceDocument;

@Repository
public interface DocumentPlaceRepository extends ElasticsearchRepository<PlaceDocument, String> {
}
