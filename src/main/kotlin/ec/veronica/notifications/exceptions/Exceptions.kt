package ec.veronica.subscriptions.exceptions

open class DomainException(message: String) : RuntimeException(message)

class AlreadyExistsException(override val message: String) : DomainException(message)

class ResourceNotFoundException(override val message: String) : DomainException(message)