#include <stdio.h>


int main() {
	FILE *fvertex = fopen("node.in", "r");
	FILE *farc = fopen("link.in", "r");
	FILE *fout = fopen("gps.html", "w");
	FILE *fpath = fopen("node.in", "r");

	double lat, lng;
	double centx, centy;
	double from_lat, from_lng, to_lat, to_lng;
	int traffic;
	int i = 0, j = 0, k = 0;
	double arcs1[55][55];
	int arcs1_count;

/* ====  html code ==== */
	// head
	fprintf(fout, "<!DOCTYPE html>\n"
			"<html>\n"
			"	<head>\n"
			"		<style type=\"text/css\">\n"
			"			html, body {height: 100%; margin: 0; padding: 0; }\n"
			"			#map { height: 100%; }\n"
			"		</style>\n"
			"	</head>\n\n");

	// body
	fprintf(fout, "	<body>\n"
			"		<div id=\"map\"></div>\n"
			"		<script type=\"text/javascript\">\n\n");


	// vertex
	fscanf(fvertex, "%lf %lf", &lat, &lng);
	centx = lat;
	centy = lng;

	fprintf(fout, "		var vertex = [\n"
			"			{lat: %lf, lng: %lf}", lat, lng);


	while (fscanf(fvertex, "%lf %lf", &lat, &lng) != EOF) {
		fprintf(fout, ",\n			{lat: %lf, lng: %lf}", lat, lng);
	}
	fprintf(fout, "\n		];\n\n");

	fprintf(fout, "		var markers = [];\n"
			"		var map;\n\n");


	while(fscanf(farc, "%lf %lf %lf %lf", &from_lat, &from_lng, &to_lat, &to_lng) != EOF) {
			arcs1[i][0] = from_lat;
			arcs1[i][1] = from_lng;
			arcs1[i][2] = to_lat;
			arcs1[i][3] = to_lng;
			i++;
	}
	arcs1_count = i;

	// arcs1
	fprintf(fout, "		var arcs1 = [\n"
			"			[{lat: %lf, lng: %lf}, {lat: %lf, lng: %lf}]", arcs1[0][0], arcs1[0][1], arcs1[0][2], arcs1[0][3]);
	for(i = 1; i<arcs1_count; i++) {	
		fprintf(fout, ",\n			[{lat: %lf, lng: %lf}, {lat: %lf, lng: %lf}]", arcs1[i][0], arcs1[i][1], arcs1[i][2], arcs1[i][3]);
	}
	fprintf(fout, "\n		];\n\n");

	// path
	fscanf(fpath, "%lf %lf", &lat, &lng);

	fprintf(fout, "		var paths = [\n"
			"			{lat: %lf, lng: %lf}", lat, lng);

	while (fscanf(fpath, "%lf %lf", &lat, &lng) != EOF) {
		fprintf(fout, ",\n			{lat: %lf, lng: %lf}", lat, lng);
	}
	fprintf(fout, "\n		];\n\n");


	// init map
	fprintf(fout, "		function initMap() {\n"
			"			var mylatlng = new google.maps.LatLng(%lf, %lf);\n"
			"			var mapOptions = {\n"
			"				zoom: 16,\n"
			"				center: mylatlng\n"
			"			};\n\n"

			"			map = new google.maps.Map(document.getElementById(\"map\"), mapOptions);\n\n"

			// polyline
			"			for (var i = 0; i < arcs1.length; i++) {\n"
			"				var poly = new google.maps.Polyline({\n"
			"					path: arcs1[i],\n"
			"					strokeColor: '#0000FF',\n"
			"					strokeWeight: 5\n"
			"				});\n"
				"			poly.setMap(map);\n\n"
			"			}\n\n"

			// path
			"			var path = new google.maps.Polyline({\n"
			"				path: paths,\n"
			"				strokeColor: '#FFFFFF',\n"
			"				strokeWeight: 3\n"
			"			});\n"
			"			path.setMap(map);\n\n"
			

			// add marker
			"			for (var i = 0; i < vertex.length; i++) {\n"
			"				addMarker(vertex[i]);\n"
			"			}\n\n"
			"			showMarkers();\n"
			"		}\n\n");


	// marker function
	fprintf(fout, "		function addMarker(location) {\n"
			"			var marker = new google.maps.Marker({\n"
			"				position: location,\n"
			"				map: map\n"
			"			});\n"
			"			markers.push(marker);\n"
			"		}\n\n");

	// set map all markers
	fprintf(fout, "		function setMapOnAll(map) {\n"
			"			for (var i = 0; i < markers.length; i++) {\n"
			"				markers[i].setMap(map);\n"
			"			}\n"
			"		}\n\n");

	// show markers
	fprintf(fout, "		function showMarkers() {\n"
			"			setMapOnAll(map);\n"
			"		}\n"
			"		</script>\n\n");

	// key
	fprintf(fout, "		<script async defer\n"
			"			src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyAQVAUMDX-HrPI1jEPybVreQPF-kXrWbF4&callback=initMap\">\n"
			"		</script>\n"
			"	</body>\n"
			"</html>");

	fclose(fvertex);
	fclose(farc);
	fclose(fout);
}
