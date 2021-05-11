package com.moment.the.repository;

import com.moment.the.domain.TableDomain;
import com.moment.the.dto.AmountUncomfortableDto;
import com.moment.the.dto.TableViewDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableDomain, Long>{
    // idx로 table 찾기.
    Optional<TableDomain> findByBoardIdx(Long boardIdx);

    // Goods 수로 top10 정렬, limit 10
    List<TableDomain> findTop10ByOrderByGoodsDesc();

    List<TableDomain> findAllByOrderByBoardIdxDesc();

    List<TableDomain> findTop1ByOrderByBoardIdxDesc();

    @Query("SELECT new com.moment.the.dto.TableViewDto(table.boardIdx, table.content, table.goods, answer)" +
            "FROM TableDomain table LEFT JOIN table.answerDomain answer " +
            "ORDER BY table.boardIdx DESC "
    )
    List<TableViewDto> tableViewAll();

    @Query("SELECT new com.moment.the.dto.TableViewDto(table.boardIdx, table.content, table.goods, answer)" +
            "FROM TableDomain table LEFT JOIN table.answerDomain answer " +
            "ORDER BY table.goods DESC "
    )
    List<TableViewDto> tableViewTopBy(Pageable p);
}
