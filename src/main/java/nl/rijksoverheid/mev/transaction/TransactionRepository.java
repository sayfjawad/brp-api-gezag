package nl.rijksoverheid.mev.transaction;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository voor transactions
 */
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
