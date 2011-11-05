#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <sys/types.h>
#include <netinet/in.h> // possibly unneeded

/* error processing */
void error(char *msg)
{
	perror(msg);
	exit(1);
}

int main(int argc, char *argv[])
{
	int sockfd, newsockfd; // file descriptors
	int portno;            // port number (local)
	int clilen;            // size of client address
	int n;                 // num chars from read() and write()

	char buffer[256];      // buffer for socket data

	struct sockaddr_in serv_addr, cli_addr; // client and server IP addresses

	/* create socket */
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if( sockfd < 0 )
		error("ERROR opening socket\n");

	/* initialize serv_addr to zero */
	bzero((char *) &serv_addr, sizeof(serv_addr));

	/* static port number */
	portno = 51393;

	/* configure serv_addr */
	serv_addr.sin_family = AF_INET;         // use internet addresses
	serv_addr.sin_port = htons(portno);     // set port
	serv_addr.sin_addr.s_addr = INADDR_ANY; // use current IP address for server

	/* bind the socket to the address */
	if( bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0 )
		error("ERROR on binding\n");


	printf("Socket bound.  Welcome to WeJay!\n");

	while (1)
	{
		/* listen for connections */
		listen( sockfd, 5 );

		/* block until a client connects */
		clilen = sizeof( cli_addr );
		newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
		if( newsockfd < 0 )
			error("ERROR on accept\n");

		/* clear buffer */
		bzero(buffer, 256);

		/* read from wire */
		n = read(newsockfd, buffer, 255);
		if( n < 0 )
			error("ERROR reading from socket\n");
		printf("Message from client: %s", buffer);

		/* normally, you would respond here, but the client doesn't handle that
		 * yet anyway. */
	}

	return 0;
}
