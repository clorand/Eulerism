
closeAllConnections()
rm(list=ls())
setwd("C:/eclipse/git/Eulerism/Euler2.0/src/studioR")
#libraries
library(rgl)

#geodesic equations final version

n     <- 100
A     <- 1
rho   <- rep(1,n)
theta <- pi/(2*n) * (0:n-1)
phi   <- rep(0,n)

nanobot.phi <- function(theta_, A=1)
{
  formula  <- 2*atan(A*tan(theta_/2)^(-sqrt(3)))-pi/2
  formula[is.nan(formula)] <- 0
  nanobot.phi <- formula
}

phi   <- nanobot.phi(theta, 0.1)

polar.to.spherical <- function(rho_, theta_, phi_)
{
  dataRows <- NULL
  
  for (i in (1:length(rho_)))
  {
    dataRows <- rbind(dataRows,
                        cbind(
                            rho_[i]*sin(theta_[i])*cos(phi_[i]),
                            rho_[i]*sin(theta_[i])*sin(phi_[i]), 
                            rho_[i]*cos(theta_[i])
                           )
                          )
  }
  polar.to.spherical <- dataRows
}


braid <- addNormals(cylinder3d(polar.to.spherical(rho, theta, phi), radius = 0.01))
shade3d(braid, col="yellow")

braid <- addNormals(cylinder3d(polar.to.spherical(rho, theta, phi+2*pi/3), radius = 0.01))
shade3d(braid, col="yellow")

braid <- addNormals(cylinder3d(polar.to.spherical(rho, theta, phi+4*pi/3), radius = 0.01))
shade3d(braid, col="yellow")




