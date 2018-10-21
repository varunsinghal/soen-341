package com.soen.empower.repository;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.Card;

import java.util.List;

/**
 * The interface class which extends the class CrudRepository. It uses Spring data's mission to
 * provide a familiar and consisten spring based programming model for data access. It retains the
 * speical traits of the underlying data store.
 * It makes it easy to use data access technologies, relational and non-relational databases,
 * and cloud-based data services.
 * <p>
 *     This interface works as a gateway for the service layer to access the data in the database.
 *     As, noted here, there are no hard coded queries required. It is advanced integration with Spring
 *     MVC controllers. It has the possibility to extend custom repository code.
 * </p>
 */
public interface CardRepository extends CrudRepository<Card, String>{
    
    /**
     * This method is responsible to search for the cards in the database. The sort is applied in
     * reverse order to send the cards in descending order of their ids. Since, id column is a
     * sequence no. and as suggested by the feedback from the course reviewer that the feeds must
     * be displayed in reverse order.
     *
     * Hence, we are using the method with order by clause.
     *
     * Key features to note here:
     * <ul>
     *     <li>This method is implemented at the run time.</li>
     *     <li>The implementation is taken care by the Hibernate.</li>
     * </ul>
     *
     * @return the list of cards with descending order.
     */
    public List<Card> findAllByOrderByIdDesc();

}
