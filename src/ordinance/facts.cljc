(ns ordinance.facts
  "Municipal-ordinance compliance catalog for San José (Cantón
  Central, Costa Rica) -- the NINETEENTH municipality-level entry (see
  cloud-itonami-municipality-jpn-tokyo, -usa-washington-dc, -gbr-london,
  -can-toronto, -deu-berlin, -fra-paris, -nld-amsterdam, -esp-madrid,
  -kor-seoul, -ita-roma, -aus-sydney, -arg-buenos-aires, -fin-helsinki,
  -dnk-copenhagen, -nor-oslo, -bel-brussels, -chl-santiago, -col-bogota
  for the first eighteen) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  msj.go.cr (the Municipality's own domain) was entirely unreachable
  (connect ECONNREFUSED 196.40.1.83:443) -- abandoned without forcing
  it. Every entry here instead cites pgrweb.go.cr (SCIJ -- Sistema
  Costarricense de Información Jurídica), which also indexes
  municipal/cantonal regulations alongside national law, and which
  already proved reliable for this country's statute.facts earlier
  this session -- never fabricated. An ordinance not in this table has
  NO spec-basis, full stop; extend `catalog`, do not invent an
  id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"san-jose"
   [{:ordinance/id "san-jose.reglamento-autonomo-organizacion-servicio"
     :ordinance/title "Reglamento Autónomo de Organización y Servicio de la Municipalidad de San José"
     :ordinance/municipality "san-jose"
     :ordinance/country "CRI"
     :ordinance/kind :ordinance
     :ordinance/number "N.º 472"
     :ordinance/url "https://pgrweb.go.cr/scij/Busqueda/Normativa/Normas/nrm_texto_completo.aspx?param1=NRTC&nValor1=1&nValor2=25393&nValor3=75923&strTipM=TC"
     :ordinance/url-provenance :official-pgrweb-go-cr
     :ordinance/enacted-date "1997-08-26"
     :ordinance/last-revised-date "2009-03-03"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:governance}}
    {:ordinance/id "san-jose.reglamento-publicidad-exterior"
     :ordinance/title "Reglamento de Publicidad Exterior (Reglamentos de Desarrollo Urbano del Cantón de San José)"
     :ordinance/municipality "san-jose"
     :ordinance/country "CRI"
     :ordinance/kind :ordinance
     :ordinance/url "https://pgrweb.go.cr/scij/busqueda/normativa/normas/nrm_texto_completo.aspx?nValor1=1&nValor2=77325"
     :ordinance/url-provenance :official-pgrweb-go-cr
     :ordinance/enacted-date "1995-01-24"
     :ordinance/last-revised-date "2023-12-21"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:urban-planning :public-order}}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-cri-san-jose Wave 0 (ADR-2607141700): "
                 (count (get catalog "san-jose")) " San José entries seeded "
                 "with an official pgrweb.go.cr citation. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
