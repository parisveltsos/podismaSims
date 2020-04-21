cd ~/git/podismaSims/output/SIMNAME

for i in $(ls | grep txt); do cat <(seq 1 1000 | perl ~/git/podismaSims/scripts/transposeTabDelimited.pl) <(perl -pe 's/Generation /Generation/ ; s/ /\t/g' $i | cut -f 3- | awk '{sum=0; for (i=1; i<=NF; i++) { sum+= $i } print sum}' | awk 'ORS=NR%40?FS:RS' | head -1000 | perl ~/git/podismaSims/scripts/transposeTabDelimited.pl) > Rinput_$i; done

tail -20 Repl0TrObservedZone.txt | grep -v 'Width\|Nbr\|every' | perl -pe "s/\'/\"/ ; s/\:\'/\"\t/ ; s/\:/\"\t/ ; s/\'//" > table.txt

Rscript ~/git/podismaSims/scripts/plotStochastic.r

open clinePlotStoc.pdf fitnessPlotStoc.pdf
