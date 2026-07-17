(ns culture.facts
  "Regional-culture catalog for San Jose (Costa Rica) -- local dishes,
  protected products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"san-jose"
   [{:culture/id "san-jose.dish.gallo-pinto"
     :culture/name "Gallo pinto"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :dish
     :culture/summary "Traditional Central American rice-and-bean dish; Costa Rica's best-known dish and iconic national breakfast staple, widely eaten in San Jose."
     :culture/url "https://en.wikipedia.org/wiki/Gallo_pinto"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "san-jose.dish.casado"
     :culture/name "Casado"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :dish
     :culture/summary "Traditional home-cooked Costa Rican meal of rice, black beans, plantains, salad, a tortilla and an optional protein such as chicken, beef, pork or fish."
     :culture/url "https://en.wikipedia.org/wiki/Casado"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "san-jose.dish.chifrijo"
     :culture/name "Chifrijo"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :dish
     :culture/summary "Contemporary Costa Rican dish known since the 1970s that originated in the bars of the capital, combining chicharrones, chimichurri (pico de gallo) and beans."
     :culture/url "https://es.wikipedia.org/wiki/Chifrijo"
     :culture/url-provenance :wikipedia-es
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "san-jose.dish.olla-de-carne"
     :culture/name "Olla de carne"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :dish
     :culture/summary "Traditional Costa Rican soup of broth with pieces of meat and abundant vegetables, primarily consumed at midday meals."
     :culture/url "https://es.wikipedia.org/wiki/Olla_de_carne"
     :culture/url-provenance :wikipedia-es
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "san-jose.beverage.guaro"
     :culture/name "Guaro"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :beverage
     :culture/summary "Sugar-cane liquor strongly associated with Costa Rica through Cacique Guaro, since 1980 the only legal brand, produced by the state-owned National Liquor Factory."
     :culture/url "https://en.wikipedia.org/wiki/Guaro_(drink)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "san-jose.product.costa-rican-coffee"
     :culture/name "Costa Rican coffee"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :product
     :culture/summary "Coffee grown notably in the Central Valley, the region around San Jose; coffee production has played a key role in Costa Rica's history and remains important to its economy."
     :culture/url "https://en.wikipedia.org/wiki/Coffee_production_in_Costa_Rica"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "san-jose.festival.festival-de-la-luz"
     :culture/name "Festival de la Luz"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :festival
     :culture/summary "Popular festival held in the city of San Jose on the second or third Saturday of December, with float parades, masquerades, live music and fireworks opening the Christmas season."
     :culture/url "https://es.wikipedia.org/wiki/Festival_de_la_Luz"
     :culture/url-provenance :wikipedia-es
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "san-jose.heritage.national-theatre"
     :culture/name "National Theatre of Costa Rica"
     :culture/name-local "Teatro Nacional de Costa Rica"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :heritage
     :culture/summary "Neoclassical theatre in central San Jose, opened 21 October 1897 during Costa Rica's coffee-export prosperity; a cultural landmark of the city."
     :culture/url "https://en.wikipedia.org/wiki/National_Theatre_of_Costa_Rica"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "san-jose.heritage.national-museum"
     :culture/name "National Museum of Costa Rica"
     :culture/name-local "Museo Nacional de Costa Rica"
     :culture/municipality "san-jose"
     :culture/country "CRI"
     :culture/kind :heritage
     :culture/summary "Costa Rica's national museum in San Jose, housed since 1950 in the 1917 Bellavista building that originally served as military barracks."
     :culture/url "https://en.wikipedia.org/wiki/National_Museum_of_Costa_Rica"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-municipality-cri-san-jose culture catalog "
                 "(ADR-2607171400): " (count (get catalog "san-jose"))
                 " San Jose entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
