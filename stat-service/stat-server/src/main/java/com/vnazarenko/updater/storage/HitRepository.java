package com.vnazarenko.updater.storage;

import com.vnazarenko.updater.dto.StatDto;
import com.vnazarenko.updater.model.Hit;
import com.vnazarenko.updater.model.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс класса-репозитория HIT
 */
public interface HitRepository extends JpaRepository<Hit, Long> {

    @Query(value = "select new com.vnazarenko.updater.model.Stat( h.app, h.uri, count(h.ipAddress)) " +
            "from Hit as h " +
            "where h.hitDate between :start and :end " +
            "group by h.app, h.uri " +
            "order by count(h.ipAddress) desc")
    List<Stat> getHits(@Param("start") LocalDateTime start,
                          @Param("end") LocalDateTime end);

    @Query(value = "select new com.vnazarenko.updater.model.Stat(h.app, h.uri, count(distinct h.ipAddress)) " +
            " from Hit as h " +
            " where h.hitDate between :start and :end " +
            " group by h.app, h.uri " +
            " order by count(distinct h.ipAddress) desc ")
    List<Stat> getUniqueHits(@Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end);

    @Query(value = "select new com.vnazarenko.updater.model.Stat(h.app, h.uri, count(h.ipAddress)) " +
            " from Hit as h " +
            " where h.hitDate between :start and :end " +
            "   and h.uri in :uris " +
            " group by h.app, h.uri " +
            " order by count(h.ipAddress) desc ")
    List<Stat> getHitsByUris(@Param("uris") List<String> uris,
                             @Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end);

    @Query(value = "select new com.vnazarenko.updater.model.Stat(h.app, h.uri, count(distinct h.ipAddress)) " +
            " from Hit as h " +
            " where h.hitDate between :start and :end " +
            "   and h.uri in :uris " +
            " group by h.app, h.uri " +
            " order by count(distinct h.ipAddress) desc ")
    List<Stat> getUniqueHitsByUris(@Param("uris") List<String> uris,
                                   @Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end);
}