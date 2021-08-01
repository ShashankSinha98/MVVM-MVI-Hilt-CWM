package com.example.mvvm_mvihiltcwm.util

/**
 *      Interface providing general function for mapping Entity Model (Ex- BlogNetworkEntity) to Domain Model
 *      (Ex- Blog) and Vice-versa.
 * */

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}