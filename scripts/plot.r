datapath <- '~/git/podismaSims/output/SIMNAME'
fdata <- read.table(file.path(datapath, 'Rinput_Repl0TrObservedZone.txt'), header=T)
ydata <- read.table(file.path(datapath, 'Rinput_Repl0TrTotalY.txt'), header=T)
mtdata <- read.table(file.path(datapath, 'Rinput_Repl0TrmtDNA.txt'), header=T)
adata <- read.table(file.path(datapath, 'Rinput_Repl0TrAutosomalAllele.txt'), header=T)
ffitness <- read.table(file.path(datapath, 'Rinput_Repl0TrFemaleFitness.txt'), header=T)
mfitness <- read.table(file.path(datapath, 'Rinput_Repl0TrMaleFitness.txt'), header=T)
afitness <- read.table(file.path(datapath, 'Rinput_Repl0TrTotalFitness.txt'), header=T)
# shfemales <- read.table(file.path(datapath, 'Rinput_Repl0TrShoeFemales.txt'), header=T)
yfemales <- read.table(file.path(datapath, 'Rinput_Repl0TrOutputYinFemales.txt'), header=T)
ynfemales <- read.table(file.path(datapath, 'Rinput_Repl0TrNextToYinFemales.txt'), header=T)
# str(fdata)

pdf(file.path(datapath,'clinePlot.pdf'), width=12, height=12)
par(mfrow=c(3,2)) 
par(mar=c(5,5,4,3))
plot(seq(1:40), fdata$X1, type='n', xlab="HZ location", ylab="% cline", main="Fusion cline", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), fdata$X1)
lines(seq(1:40), fdata$X10, col=2, lw=2)
lines(seq(1:40), fdata$X20, col=3, lw=2)
lines(seq(1:40), fdata$X30, col=4, lw=2)
lines(seq(1:40), fdata$X40, col=6, lw=2)
lines(seq(1:40), fdata$X100, col=8, lw=2)
legend('bottomright', inset=0.02, legend=c('g1', 'g100', 'g200', 'g300', 'g400', 'g1000'), pch =c(1), col=c(1,2,3,4,6,8), cex=0.8 )

par(mar=c(5,5,4,3))
plot(seq(1:40), mtdata$X1, type='n', xlab="HZ location", ylab="% cline", main="Y cline", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), ydata$X1, lty=2, lw=2)
lines(seq(1:40), ydata$X10, col=2, lty=2, lw=2)
lines(seq(1:40), ydata$X20, col=3, lty=2, lw=2)
lines(seq(1:40), ydata$X30, col=4, lty=2, lw=2)
lines(seq(1:40), ydata$X40, col=6, lty=2, lw=2)
lines(seq(1:40), ydata$X100, col=8, lty=2, lw=2)
# legend('bottomright', inset=0.02, legend=c('g1', 'g100', 'g200', 'g300', 'g400', 'g1000'), pch =c(1), col=c(1,2,3,4,6,8), cex=0.8 )

par(mar=c(5,5,4,3))
plot(seq(1:40), mtdata$X1, type='n', xlab="HZ location", ylab="% cline", main="mtDNA cline", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), mtdata$X1, lty=3, lw=2)
lines(seq(1:40), mtdata$X10, col=2, lty=3, lw=2)
lines(seq(1:40), mtdata$X20, col=3, lty=3, lw=2)
lines(seq(1:40), mtdata$X30, col=4, lty=3, lw=2)
lines(seq(1:40), mtdata$X40, col=6, lty=3, lw=2)
lines(seq(1:40), mtdata$X100, col=8, lty=3, lw=2)
# legend('bottomright', inset=0.02, legend=c('g1', 'g100', 'g200', 'g300', 'g400', 'g1000'), pch =c(1), col=c(1,2,3,4,6,8), cex=0.8 )

par(mar=c(5,5,4,3))
plot(seq(1:40), adata$X1, type='n', xlab="HZ location", ylab="% cline", main="Au cline", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), adata$X1, lty=4, lw=2)
lines(seq(1:40), adata$X10, col=2, lty=4, lw=2)
lines(seq(1:40), adata$X20, col=3, lty=4, lw=2)
lines(seq(1:40), adata$X30, col=4, lty=4, lw=2)
lines(seq(1:40), adata$X40, col=6, lty=4, lw=2)
lines(seq(1:40), adata$X100, col=8, lty=4, lw=2)
# legend('bottomright', inset=0.02, legend=c('g1', 'g100', 'g200', 'g300', 'g400', 'g1000'), pch =c(1), col=c(1,2,3,4,6,8), cex=0.8 )
dev.off()

pdf(file.path(datapath,'fitnessPlot.pdf'), width=12, height=12)
par(mfrow=c(3,2)) 
par(mar=c(5,5,4,3))
plot(c(0,40), c(min(ffitness)-1,101), type='n', xlab="HZ location", ylab="Female fitness", main="F fitness", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), ffitness$X1, lty=3, lw=2)
lines(seq(1:40), ffitness$X10, col=2, lty=3, lw=2)
lines(seq(1:40), ffitness$X20, col=3, lty=3, lw=2)
lines(seq(1:40), ffitness$X30, col=4, lty=3, lw=2)
lines(seq(1:40), ffitness$X40, col=6, lty=3, lw=2)
lines(seq(1:40), ffitness$X100, col=8, lty=3, lw=2)
legend('bottomright', inset=0.02, legend=c('g1', 'g100', 'g200', 'g300', 'g400', 'g1000'), pch =c(1), col=c(1,2,3,4,6,8), cex=0.8 )

par(mar=c(5,5,4,3))
plot(c(0,40), c(min(mfitness)-1,101), type='n', xlab="HZ location", ylab="Male fitness", main="M fitness", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), mfitness$X1, lty=1, lw=2)
lines(seq(1:40), mfitness$X10, col=2, lty=1, lw=2)
lines(seq(1:40), mfitness$X20, col=3, lty=1, lw=2)
lines(seq(1:40), mfitness$X30, col=4, lty=1, lw=2)
lines(seq(1:40), mfitness$X40, col=6, lty=1, lw=2)
lines(seq(1:40), mfitness$X100, col=8, lty=1, lw=2)

par(mar=c(5,5,4,3))
plot(c(0,40), c(0, max(yfemales)+1), type='n', xlab="HZ location", ylab="Number Y females", main="Y in females", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), yfemales$X1, lty=2, lw=2)
lines(seq(1:40), yfemales$X10, col=2, lty=2, lw=2)
lines(seq(1:40), yfemales$X20, col=3, lty=2, lw=2)
lines(seq(1:40), yfemales$X30, col=4, lty=2, lw=2)
lines(seq(1:40), yfemales$X40, col=6, lty=2, lw=2)
lines(seq(1:40), yfemales$X100, col=8, lty=2, lw=2)

par(mar=c(5,5,4,3))
plot(c(0,40), c(min(afitness)-1,101), type='n', xlab="HZ location", ylab="Total fitness", main="All fitness", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), afitness$X1, lty=2, lw=2)
lines(seq(1:40), afitness$X10, col=2, lty=2, lw=2)
lines(seq(1:40), afitness$X20, col=3, lty=2, lw=2)
lines(seq(1:40), afitness$X30, col=4, lty=2, lw=2)
lines(seq(1:40), afitness$X40, col=6, lty=2, lw=2)
lines(seq(1:40), afitness$X100, col=8, lty=2, lw=2)

par(mar=c(5,5,4,3))
plot(c(0,40), c(0, max(ynfemales)+1), type='n', xlab="HZ location", ylab="Number Y neighbour females", main="Y rec locus in females", cex.main=1.8, cex.lab=1.3)
lines(seq(1:40), ynfemales$X1, lty=5, lw=2)
lines(seq(1:40), ynfemales$X10, col=2, lty=5, lw=2)
lines(seq(1:40), ynfemales$X20, col=3, lty=5, lw=2)
lines(seq(1:40), ynfemales$X30, col=4, lty=5, lw=2)
lines(seq(1:40), ynfemales$X40, col=6, lty=5, lw=2)
lines(seq(1:40), ynfemales$X100, col=8, lty=5, lw=2)
dev.off()

