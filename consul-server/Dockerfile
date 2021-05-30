FROM consul:latest
COPY entrypoint.sh /entrypoint.sh
RUN dos2unix /entrypoint.sh
COPY / /consul/config/
RUN chmod +x /entrypoint.sh
EXPOSE 8500
CMD ["/entrypoint.sh"]