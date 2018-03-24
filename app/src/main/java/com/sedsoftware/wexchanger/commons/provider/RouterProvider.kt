package com.sedsoftware.wexchanger.commons.provider

import ru.terrakok.cicerone.Router

interface RouterProvider {

  fun getCurrentRouter(): Router
}
