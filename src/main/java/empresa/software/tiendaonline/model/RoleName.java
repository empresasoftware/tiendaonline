/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.model;

/**
 *
 * @author pedro
 */
public enum  RoleName {
    ROLE_USER {
        @Override
        public String toString() {
            return "ROLE_USER";
        }
    },
    ROLE_ADMIN,
    ROLE_SHOP {
        @Override
        public String toString() {
            return "ROLE_SHOP";
        }
    }
}
