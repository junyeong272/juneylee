#include "chart.h"
#include <stdio.h>

void generate_chart_header() {

printf("\
<html>\n\
  <head>\n\
    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n\
    <script type=\"text/javascript\">\n\
      google.charts.load('current', {packages:[\"orgchart\"]});\n\
      google.charts.setOnLoadCallback(drawChart);\n\
\n\
      function drawChart() {\n\
        var data = new google.visualization.DataTable();\n\
        data.addColumn('string', 'Name');\n\
        data.addColumn('string', 'Manager');\n\
        data.addColumn('string', 'ToolTip');\n\
\n\
        // For each orgchart box, provide the name, manager, and tooltip to show.\n\
        data.addRows([\n\
");


}


void generate_chart_footer() {

printf("\
        ]);\n\
\n\
        // Create the chart.\n\
        var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));\n\
        // Draw the chart, setting the allowHtml option to true for the tooltips.\n\
        chart.draw(data, {allowHtml:true});\n\
      }\n\
   </script>\n\
    </head>\n\
  <body>\n\
    <div id=\"chart_div\"></div>\n\
  </body>\n\
</html>\n\
");


}


void generate_chart_node(char* name, char* boss, int score) {

printf("\
          ['%s', '%s', '%d'],\n\
", name, boss, score);


}
