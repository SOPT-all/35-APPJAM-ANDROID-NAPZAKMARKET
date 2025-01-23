package com.napzak.market.domain.registration.usecase

import com.napzak.market.domain.registration.model.BuyProduct
import com.napzak.market.domain.registration.model.Product
import com.napzak.market.domain.registration.model.SellProduct
import com.napzak.market.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class ProductRegistrationUseCase @Inject constructor(
    private val registrationRepository: RegistrationRepository,
) {
    suspend operator fun invoke(product: Product): Result<Long> {
        return when (product) {
            is BuyProduct -> registrationRepository.postBuyRegistration(product)
            is SellProduct -> registrationRepository.postSellRegistration(product)
            else -> Result.failure(IllegalArgumentException("Invalid product type"))
        }
    }
}
