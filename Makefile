# Available docker containers
CONTAINERS=app webapp

#####################################################
#							 						#
# 							 						#
# RUNTIME TARGETS			 						#
#							 						#
#							 						#
#####################################################

default: run

# Start the containers
run: prerequisite build

# Start individual container
start: prerequisite valid-container
	- docker-compose -f docker-compose.yml up --build $(filter-out $@,$(MAKECMDGOALS))

# Stop individual container
stop: prerequisite valid-container
	- docker-compose -f docker-compose.yml stop $(filter-out $@,$(MAKECMDGOALS))

# Halts the docker containers
halt: prerequisite
	- docker-compose -f docker-compose.yml kill

#####################################################
#							 						#
# 							 						#
# SETUP AND BUILD TARGETS			 				#
#							 						#
#							 						#
#####################################################

# Build and prepare the docker containers and the project
build: prerequisite build-containers update-dependencies

# Build and launch the containers
build-containers:
	- docker-compose -f docker-compose.yml up --build

# Update the project and the dependencies
update-dependencies:
	# Update the yarn dependencies
	- docker-compose -f docker-compose.yml exec webapp sh -c "cd /app && yarn install"

# Remove the docker containers
clean: prerequisite prompt-continue
	# Remove the docker containers, networks and volumes
	- docker-compose -f docker-compose.yml rm -svf
	- docker-compose -f docker-compose.yml down --rmi all -v --remove-orphans

# Echos the container status
status: prerequisite
	- docker-compose -f docker-compose.yml ps

#####################################################
#							 						#
# 							 						#
# BASH CLI TARGETS			 						#
#							 						#
#							 						#
####################################################

# Opens a bash prompt to the php cli container
bash-app: prerequisite
	- docker-compose -f docker-compose.yml exec app bash

# Opens a bash prompt to the php fpm container
bash-webapp: prerequisite
	- docker-compose -f docker-compose.yml exec webapp sh

# Start the development server
webapp-dev:
	- docker-compose -f docker-compose.yml exec webapp sh -c "PORT=5001 yarn start"

#####################################################
#							 						#
# 							 						#
# TEST TARGETS			 						    #
#							 						#
#							 						#
####################################################

# Launch the application CI tests
test-ci: test-app test-webapp

# Launch the JUnit tests
test-app:
	- @echo "Start the App cli tests";
	- docker-compose -f docker-compose.yml exec app bash -c "cd /app && ./gradlew test"

# Launch the JS CI tests
test-webapp:
	- @echo "Start the Webapp cli tests";
	- docker-compose -f docker-compose.yml exec webapp sh -c "cd /webapp && yarn install && yarn run test"

#####################################################
#							 						#
# 							 						#
# INTERNAL TARGETS			 						#
#							 						#
#							 						#
####################################################

# Validates the prerequisites such as environment variables
prerequisite: check-environment
	- @echo "pwd "$(shell pwd)
-include .env
export ENV_FILE = $(ENVIRONMENT_FILE)

# Validates the environment variables
check-environment:
	@echo "Validating the environment";

# Check whether the docker binary is available
ifeq (, $(shell which docker-compose))
	$(error "No docker-compose in $(PATH), consider installing docker")
endif

# Check whether the mysql-cli binary is available
ifeq (, $(shell which mysql))
	$(error "No mysql-cli in $(PATH), consider installing mysql-cli")
endif

# Validates the containers
valid-container:
ifeq ($(filter $(filter-out $@,$(MAKECMDGOALS)),$(CONTAINERS)),)
	$(error Invalid container provided "$(filter-out $@,$(MAKECMDGOALS))")
endif

# Prompt to continue
prompt-continue:
	@while [ -z "$$CONTINUE" ]; do \
		read -r -p "Would you like to continue? [y]" CONTINUE; \
	done ; \
	if [ ! $$CONTINUE == "y" ]; then \
        echo "Exiting." ; \
        exit 1 ; \
    fi

%:
	@: