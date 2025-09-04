//package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.specification;
//
//
//public class CatalogDeveloperSpecification {
//    public static Specification<PortfolioDeveloper> nameContains(String name) {
//        return (root, query, cb) ->
//                name == null ? null : cb.like(cb.lower(root.get("profile").get("name")), "%" + name.toLowerCase() + "%");
//    }
//
//    public static Specification<PortfolioDeveloper> emailEquals(String email) {
//        return (root, query, cb) ->
//                email == null ? null : cb.equal(root.get("profile").get("email"), email);
//    }
//
//    public static Specification<PortfolioDeveloper> hasLanguage(String language) {
//        return (root, query, cb) -> {
//            if (language == null) return null;
//            Join<PortfolioDeveloper, Environment> joinLang = root.join("languages");
//            return cb.equal(cb.lower(joinLang.get("name")), language.toLowerCase());
//        };
//    }
//}